/*******************************************************************************
 * Copyright (c) 2011, 2015 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.bindings.Trigger;
import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.bindings.keys.KeyLookupFactory;
import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.SWTKeySupport;
import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlExtension;
import org.eclipse.jface.text.IInformationControlExtension2;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.e3.ui.dialogs.FilteredTree;
import org.eclipse.sirius.ext.e3.ui.dialogs.PatternFilter;
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
import org.eclipse.swt.widgets.TreeItem;

import com.google.common.base.Preconditions;

/**
 * Create a quick outline pop-up.
 * 
 * @author bgrouhan
 */
public class QuickOutlineControl extends PopupDialog implements IInformationControl, IInformationControlExtension, IInformationControlExtension2, DisposeListener {

    /**
     * The listener used for the tree part of the quick outline.
     * 
     * @author bgrouhan
     * 
     */
    private final class TreeKeyListener extends KeyAdapter {
        private final Tree tree;

        private TreeKeyListener(Tree tree) {
            this.tree = tree;
        }

        @Override
        public void keyPressed(KeyEvent event) {
            int accelerator = SWTKeySupport.convertEventToUnmodifiedAccelerator(event);
            KeySequence keySequence = KeySequence.getInstance(SWTKeySupport.convertAcceleratorToKeyStroke(accelerator));
            if (keySequence.getTriggers().length == 1) {
                Trigger trigger = keySequence.getTriggers()[0];
                if (trigger instanceof KeyStroke) {
                    KeyStroke keyStroke = (KeyStroke) trigger;
                    IKeyLookup lookup = KeyLookupFactory.getDefault();
                    if (keyStroke.getModifierKeys() == lookup.getCtrl() && keyStroke.getNaturalKey() == 'O') {
                        event.doit = false;
                        gotoNextPage();
                    }
                }
            }
            if (event.character == SWT.ESC || event.character == ESCAPE_CHAR_CODE) {
                dispose();
            }
            if (event.keyCode == SWT.CR) {
                gotoSelectedElement();
                return;
            }
            // clear all matches previously stored in the label provider
            ((IQuickOutlineLabelProvider) treeViewer.getLabelProvider()).clear();
            TreeItem target = null;
            if (event.keyCode == SWT.ARROW_DOWN) {
                target = getTargetDown();
            }
            if (event.keyCode == SWT.ARROW_UP) {
                target = getTargetUp();
            }
            if (target != null) {
                event.doit = false;
                tree.setSelection(new TreeItem[] { target });
            }
            setFocus();
        }
    }

    /**
     * A customized pattern filter.
     * 
     * @author bgrouhan
     * 
     */
    private final class CustomPatternFilter extends PatternFilter {

        @Override
        protected boolean isLeafMatch(Viewer viewer, Object element) {
            boolean result = false;
            IQuickOutlineLabelProvider labelProvider = (IQuickOutlineLabelProvider) ((StructuredViewer) viewer).getLabelProvider();
            String labelText = labelProvider.getText(element);
            if (labelText != null) {
                result = wordMatches(labelText);
            }
            String filterText = filteredTree.getFilterControl().getText();
            if (!filterText.equals("") && !filterText.equals("*")) { //$NON-NLS-1$ //$NON-NLS-2$
                EObject eObj = (EObject) element;
                boolean match;
                for (EAttribute attribute : eObj.eClass().getEAllAttributes()) {
                    if (attribute.getEAttributeType().getInstanceClass() == String.class && eObj.eGet(attribute) != null) {
                        match = wordMatches(eObj.eGet(attribute).toString());
                        if (match) {
                            // add match in the label provider so that the label
                            // is updated
                            labelProvider.addMatch(eObj, attribute.getName());
                        }
                        result = result || match;
                    }
                }
            }
            return result;
        }

        @Override
        public boolean isElementVisible(Viewer viewer, Object element) {
            // same as the super class, but the order is reversed so that
            // isLeafMatch is called for every element in the tree, and not only
            // those returning false for isParentMatch.
            return isLeafMatch(viewer, element) || isParentMatch(viewer, element);
        }

        public ArrayList<TreeItem> getMatchingItems(Viewer viewer, TreeItem[] items, ArrayList<TreeItem> listItems) {
            ArrayList<TreeItem> resultList = listItems;
            for (TreeItem item : items) {
                Object data = item.getData();
                if (data != null && isLeafMatch(viewer, data)) {
                    resultList.add(item);
                }
                resultList = getMatchingItems(viewer, item.getItems(), resultList);
            }
            return resultList;
        }
    }

    private static final int ESCAPE_CHAR_CODE = 0x1B;

    /** The filtered tree we're displaying. */
    private FilteredTree filteredTree;

    /** Actual viewer displaying the outline. */
    private TreeViewer treeViewer;

    /**
     * The quick outline descriptor.
     */
    private QuickOutlineDescriptor descriptor;

    /**
     * The current page.
     */
    private QuickOutlinePageDescriptor currentPage;

    /**
     * Creates an information control with the given shell as parent.
     * 
     * @param parentShell
     *            The parent of this control's shell.
     * @param shellStyle
     *            The shell style.
     * @param descriptor
     *            The quick outline descriptor.
     */
    public QuickOutlineControl(Shell parentShell, int shellStyle, QuickOutlineDescriptor descriptor) {
        super(parentShell, shellStyle, true, true, true, true, false, null, null);
        this.descriptor = descriptor;
        org.eclipse.sirius.ext.base.Option<QuickOutlinePageDescriptor> firstPage = this.descriptor.getFirstPage();

        Preconditions.checkArgument(firstPage.some(), "The descriptor has no page");

        this.currentPage = firstPage.get();
        create();
    }

    @Override
    public void addDisposeListener(DisposeListener listener) {
        getShell().addDisposeListener(listener);
    }

    @Override
    public void addFocusListener(FocusListener listener) {
        getShell().addFocusListener(listener);
    }

    @Override
    public Point computeSizeHint() {
        return getShell().getSize();
    }

    @Override
    public final void dispose() {
        filteredTree.dispose();
        close();
    }

    @Override
    public boolean hasContents() {
        return true;
    }

    @Override
    public boolean isFocusControl() {
        return getShell() == Display.getCurrent().getActiveShell();
    }

    @Override
    public void removeDisposeListener(DisposeListener listener) {
        getShell().removeDisposeListener(listener);
    }

    @Override
    public void removeFocusListener(FocusListener listener) {
        getShell().removeFocusListener(listener);
    }

    @Override
    public void setBackgroundColor(Color background) {
        applyBackgroundColor(background, getContents());
    }

    @Override
    public void setFocus() {
        getShell().forceFocus();
        filteredTree.setFocus();
    }

    @Override
    public void setForegroundColor(Color foreground) {
        applyForegroundColor(foreground, getContents());
    }

    @Override
    public void setInformation(String information) {
        // We're implementing IInformationControlExtension2, this will not be
        // called
    }

    @Override
    public void setInput(Object input) {
        treeViewer.setInput(input);
        treeViewer.expandToLevel(3);
    }

    @Override
    public void setLocation(Point location) {
        // Only override the shell's location if it's not persisted by the
        // PopupDialog
        getShell().setLocation(location);
    }

    @Override
    public void setSize(int width, int height) {
        getShell().setSize(width, height);
    }

    @Override
    public void setSizeConstraints(int maxWidth, int maxHeight) {
        // We'll use the dialog's size
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            open();
        } else {
            saveDialogBounds(getShell());
            getShell().setVisible(false);
        }
    }

    @Override
    public void widgetDisposed(DisposeEvent event) {
        filteredTree = null;
        treeViewer = null;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        createTreeViewer(parent);
        setInfoText(this.currentPage.getPageDescription());
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
        this.currentPage.getQuickOutlineCallback().select(selectedElement);
        dispose();
    }

    /**
     * Creates the outline's tree viewer.
     * 
     * @param parent
     *            the parent composite.
     */
    protected void createTreeViewer(Composite parent) {
        filteredTree = new FilteredTree(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL, new CustomPatternFilter(), true) {
            @Override
            protected void updateToolbar(boolean visible) {
                super.updateToolbar(visible);
                treeViewer.expandToLevel(3);
            }

            @Override
            protected void updateTreeSelection(boolean setFocus) {
                super.updateTreeSelection(setFocus);
                Tree t = treeViewer.getTree();
                if (getFilterControl().getText().trim().length() == 0 && t.getSelectionCount() == 0 && t.getItemCount() > 0) {
                    TreeItem root = t.getItem(0);
                    if (root != null) {
                        t.setSelection(root);
                    }
                }
            }
        };
        filteredTree.setQuickSelectionMode(true);
        treeViewer = filteredTree.getViewer();
        final Tree tree = treeViewer.getTree();

        filteredTree.getFilterControl().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                int accelerator = SWTKeySupport.convertEventToUnmodifiedAccelerator(event);
                KeySequence keySequence = KeySequence.getInstance(SWTKeySupport.convertAcceleratorToKeyStroke(accelerator));
                if (keySequence.getTriggers().length == 1) {
                    Trigger trigger = keySequence.getTriggers()[0];
                    if (trigger instanceof KeyStroke) {
                        KeyStroke keyStroke = (KeyStroke) trigger;
                        IKeyLookup lookup = KeyLookupFactory.getDefault();
                        if (keyStroke.getModifierKeys() == lookup.getCtrl() && keyStroke.getNaturalKey() == 'O') {
                            event.doit = false;
                            gotoNextPage();
                        }
                    }
                }
                if (event.keyCode == SWT.CR) {
                    gotoSelectedElement();
                    return;
                }
                // clear all matches previously stored in the label provider
                ((IQuickOutlineLabelProvider) treeViewer.getLabelProvider()).clear();
                TreeItem target = null;
                if (event.keyCode == SWT.ARROW_DOWN) {
                    target = getTargetDown();
                }
                if (event.keyCode == SWT.ARROW_UP) {
                    target = getTargetUp();
                }
                if (target != null) {
                    event.doit = false;
                    tree.setSelection(new TreeItem[] { target });
                }
                setFocus();
            }
        });

        tree.addKeyListener(new TreeKeyListener(tree));

        tree.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                // do nothing
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                gotoSelectedElement();
            }
        });

        treeViewer.setContentProvider(this.currentPage.getContentProvider());
        treeViewer.setLabelProvider(this.currentPage.getLabelProvider());
    }

    private void gotoNextPage() {
        Option<QuickOutlinePageDescriptor> newPage = this.descriptor.getNextPage(this.currentPage);
        if (newPage.some() && newPage.get() != this.currentPage) {
            // change page.
            this.currentPage = newPage.get();
            this.treeViewer.setContentProvider(this.currentPage.getContentProvider());
            this.treeViewer.setLabelProvider(this.currentPage.getLabelProvider());
            this.setInfoText(this.currentPage.getPageDescription());
        }
    }

    /**
     * Method to retrieve the next matching element.
     * 
     * @return the first TreeItem element matching the filter after the actual
     *         selected item (if there was one and if it was matching the
     *         filter).
     */
    private TreeItem getTargetDown() {
        Object source = getSelectedElement();
        CustomPatternFilter filter = (CustomPatternFilter) filteredTree.getPatternFilter();
        Tree tree = filteredTree.getViewer().getTree();
        ArrayList<TreeItem> matchingItems = filter.getMatchingItems(filteredTree.getViewer(), tree.getItems(), new ArrayList<TreeItem>());
        if (matchingItems.size() == 0) {
            return null;
        }
        boolean sourceFound = false;
        int i = 0;
        TreeItem target = matchingItems.get(i);
        while (i < matchingItems.size()) {
            if (!sourceFound && matchingItems.get(i).getData().equals(source)) {
                sourceFound = true;
            } else if (sourceFound) {
                target = matchingItems.get(i);
                break;
            }
            i++;
        }
        return target;
    }

    /**
     * Method to retrieve the previous matching element.
     * 
     * @return the first TreeItem element matching the filter before the actual
     *         selected item (if there was one and if it was matching the
     *         filter).
     */
    private TreeItem getTargetUp() {
        Object source = getSelectedElement();
        CustomPatternFilter filter = (CustomPatternFilter) filteredTree.getPatternFilter();
        Tree tree = filteredTree.getViewer().getTree();
        ArrayList<TreeItem> matchingItems = filter.getMatchingItems(filteredTree.getViewer(), tree.getItems(), new ArrayList<TreeItem>());
        if (matchingItems.size() == 0) {
            return null;
        }
        int i = matchingItems.size() - 1;
        TreeItem target = matchingItems.get(i);
        boolean sourceFound = false;
        while (i >= 0) {
            if (!sourceFound && matchingItems.get(i).getData().equals(source)) {
                sourceFound = true;
            } else if (sourceFound) {
                target = matchingItems.get(i);
                break;
            }
            i--;
        }
        return target;
    }

    @Override
    protected boolean hasTitleArea() {
        return false;
    }
}
