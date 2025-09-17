/*******************************************************************************
 * Copyright (c) 2015, 2025 Obeo.
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
package org.eclipse.sirius.editor.tools.internal.presentation;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.pde.core.target.ITargetDefinition;
import org.eclipse.pde.core.target.ITargetHandle;
import org.eclipse.pde.core.target.ITargetPlatformService;
import org.eclipse.pde.core.target.TargetBundle;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.progress.DeferredTreeContentManager;
import org.eclipse.ui.progress.IDeferredWorkbenchAdapter;
import org.eclipse.ui.progress.IElementCollector;

/**
 * This dialog permits to select a file in the workspace or in the plugins to
 * retrieve its full path.
 * 
 * @author bgrouhan
 *
 */
public class WorkspaceAndPluginsResourceDialog extends ElementTreeSelectionDialog {
    private List<String> extensions;

    /**
     * Constructor.
     * 
     * @param parent
     *            The parent shell.
     * @param allowMultiple
     *            True if multiple selection is allowed, false otherwise.
     * @param extensions
     *            The wanted extensions, null if there is no filter.
     */
    public WorkspaceAndPluginsResourceDialog(Shell parent, boolean allowMultiple, List<String> extensions) {
        super(parent, WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(), new DeferredWorkbenchContentProvider());
        this.extensions = new ArrayList<String>(extensions);
        setAllowMultiple(allowMultiple);
        if (allowMultiple) {
            setTitle(SiriusEditorPlugin.INSTANCE.getString("titleSelectFiles"));
        } else {
            setTitle(SiriusEditorPlugin.INSTANCE.getString("titleSelectAFile"));
        }
        setInput(new WorkbenchProxyObject());
        addFilter(new FileExtensionFilter());
    }

    @Override
    protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
        return new TreeViewer(new Tree(parent, style)) {
            @Override
            public boolean isExpandable(Object element) {
                if (element instanceof WorkbenchProxyObject && ((WorkbenchProxyObject) element).getContainedObject() instanceof ITargetDefinition) {
                    return true;
                }
                return super.isExpandable(element);
            }
        };
    }

    /**
     * Method to retrieve a list containing the full paths of selected elements.
     * 
     * @return a list, possibly empty, containing the paths of selected
     *         elements.
     */
    public List<String> getPaths() {
        ArrayList<String> paths = new ArrayList<String>();
        Object[] result = getResult();
        if (result != null) {
            for (Object obj : result) {
                if (obj instanceof IFile) {
                    paths.add(((IFile) obj).getFullPath().toString());
                } else if (obj instanceof WorkbenchProxyObject) {
                    String path = ((WorkbenchProxyObject) obj).getFilePath();
                    if (path != null) {
                        paths.add(path);
                    }
                }
            }
        }
        return paths;
    }

    /**
     * Static method opening a WorkspaceAndPluginsResourceDialog to search for
     * an image and returning the path of the selected image.
     * 
     * @param shell
     *            the parent shell.
     * @return the path of the selected image if there was one, null otherwise.
     */
    public static String openDialogForImages(Shell shell) {
        List<String> extensions = new ArrayList<String>();
        for (ImageFileFormat iff : ImageFileFormat.VALUES) {
            extensions.add(iff.getName());
        }
        WorkspaceAndPluginsResourceDialog dialog = new WorkspaceAndPluginsResourceDialog(shell, false, extensions);
        dialog.open();
        List<String> paths = dialog.getPaths();
        if (paths.size() > 0) {
            return paths.get(0);
        }
        return null;
    }

    /**
     * Class needed to simulate the behavior of a workbench element for a
     * non-workbench element.
     * 
     * @author bgrouhan
     *
     */
    private static class WorkbenchProxyObject implements IAdaptable {
        private Object containedObject;

        private String pluginPath;

        private boolean root;

        private boolean checked;

        private boolean valid = true;

        /**
         * Constructor for the root element.
         */
        WorkbenchProxyObject() {
            containedObject = null;
            root = true;
            checked = true;
        }

        /**
         * Constructor for non-root elements.
         * 
         * @param object
         *            the contained object.
         * @param pluginName
         *            the name of the plugin.
         */
        WorkbenchProxyObject(Object object, String pluginName) {
            pluginPath = pluginName;
            containedObject = object;
            root = false;
            checked = false;
        }

        /**
         * Getter for the contained object;
         * 
         * @return the contained object.
         */
        public Object getContainedObject() {
            return containedObject;
        }

        /**
         * Return whether it is a valid object or not.
         * 
         * @return true if valid, false otherwise.
         */
        public boolean isValid() {
            return valid;
        }

        /**
         * Set the validity of this object.
         * 
         * @param valid
         *            a boolean.
         */
        public void setValid(boolean valid) {
            this.valid = valid;
        }

        /**
         * Return whether this object has been already checked.
         * 
         * @return true if already checked, false otherwise.
         */
        public boolean isChecked() {
            return checked;
        }

        /**
         * Set the checked attribute.
         * 
         * @param checked
         *            a boolean.
         * 
         */
        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        /**
         * Method to get the children of the contained object. This method is
         * not called when the contained object is an instance of
         * ITargetDefinition.
         * 
         * @return the children of the contained object.
         */
        public Object[] getChildren() {
            Object[] result = new Object[0];
            if (root) {
                // returns the workspace root and a proxy containing the
                // workspace target definition if it exists, else returns only
                // the workspace root
                ITargetPlatformService service = PlatformUI.getWorkbench().getService(ITargetPlatformService.class);
                ITargetDefinition td = null;
                if (service != null) {
                    try {
                        ITargetHandle handle = service.getWorkspaceTargetHandle();
                        if (handle != null) {
                            td = handle.getTargetDefinition();
                        }
                    } catch (CoreException e) {
                    }
                }
                if (td != null) {
                    WorkbenchProxyObject wpoTargetDefinition = new WorkbenchProxyObject(td, null);
                    wpoTargetDefinition.setChecked(true);
                    result = new Object[] { ResourcesPlugin.getWorkspace().getRoot(), wpoTargetDefinition };
                } else {
                    result = new Object[] { ResourcesPlugin.getWorkspace().getRoot() };
                }
            } else if (containedObject instanceof ZipFile) {
                List<Object> children = new ArrayList<Object>();
                // returns the paths of elements directly contained by the
                // bundle root, each in a proxy
                Enumeration<? extends ZipEntry> entries = ((ZipFile) containedObject).entries();
                ZipEntry zipEntry;
                while (entries.hasMoreElements()) {
                    zipEntry = entries.nextElement();
                    if (!zipEntry.isDirectory()) {
                        children.add(new WorkbenchProxyObject(zipEntry, getLabel()));
                    }
                }
                result = children.toArray();
            }
            return result;
        }

        /**
         * Method to get the label of the contained object.
         * 
         * @return the label of the contained object.
         */
        public String getLabel() {
            String result = "";
            if (containedObject instanceof ITargetDefinition) {
                result = "Plugins";
            } else if (containedObject instanceof ZipFile) {
                result = ((ZipFile) containedObject).getName();
                int startPosition = result.lastIndexOf(File.separator) + File.separator.length();
                int endPosition = result.lastIndexOf("_");
                if (endPosition <= startPosition) {
                    // the name should end like this : "_versionNumber.jar", but
                    // in case there wasn't the versionNumber part, we will stop
                    // right before the ".jar"
                    endPosition = result.length() - 4;
                }
                result = result.substring(startPosition, endPosition);
            } else if (containedObject instanceof ZipEntry) {
                result = ((ZipEntry) containedObject).getName();
            }
            return result;
        }

        /**
         * Method to get the path of the contained object, if it corresponds to
         * a file.
         * 
         * @return the path of the contained object, if it corresponds to a
         *         file, null otherwise.
         */
        public String getFilePath() {
            if (containedObject instanceof ZipEntry) {
                String path = ((ZipEntry) containedObject).getName();
                return "/" + pluginPath + "/" + path;
            }
            return null;
        }

        /**
         * Method to get the extension of the contained object, if it is a file
         * and if it has an extenson.
         * 
         * @return the extension of the file or null.
         */
        public String getFileExtension() {
            if (containedObject instanceof ZipEntry) {
                String path = ((ZipEntry) containedObject).getName();
                int lastDot = path.lastIndexOf(".");
                if (lastDot > path.lastIndexOf(File.separator)) {
                    // there is a dot and it is in the last segment of the
                    // path, we can return what is after it
                    return path.substring(lastDot + 1);
                }
            }
            return null;
        }

        @Override
        public Object getAdapter(Class adapter) {
            Object obj = null;
            if (adapter == IWorkbenchAdapter.class || adapter == IDeferredWorkbenchAdapter.class) {
                obj = new CustomWorkbenchAdapter();
            }
            return obj;
        }
    }

    /**
     * A workbenchContent provider that permits to add children in a deferred
     * way.
     * 
     * @author bgrouhan
     *
     */
    private static final class DeferredWorkbenchContentProvider extends WorkbenchContentProvider {
        DeferredTreeContentManager manager;

        @Override
        public Object[] getChildren(Object element) {
            if (element instanceof WorkbenchProxyObject) {
                WorkbenchProxyObject wpo = (WorkbenchProxyObject) element;
                if (wpo.getContainedObject() instanceof ITargetDefinition) {
                    // we want to fetch the children of the bundle context in a
                    // deferred way, so that results appear progressively
                    return manager.getChildren(wpo);
                }
            }
            return super.getChildren(element);
        }

        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            super.inputChanged(viewer, oldInput, newInput);
            manager = new DeferredTreeContentManager((AbstractTreeViewer) viewer);
        }
    }

    /**
     * WorkbenchAdapter for non-workbench elements.
     */
    private static final class CustomWorkbenchAdapter implements IDeferredWorkbenchAdapter {
        @Override
        public String getLabel(Object object) {
            return ((WorkbenchProxyObject) object).getLabel();
        }

        @Override
        public Object[] getChildren(Object element) {
            return ((WorkbenchProxyObject) element).getChildren();
        }

        @Override
        public void fetchDeferredChildren(Object object, IElementCollector collector, IProgressMonitor monitor) {
            // this method is called uniquely for the instance of
            // WorkbenchProxyOject containing the target definition
            ITargetDefinition targetDefinition = (ITargetDefinition) ((WorkbenchProxyObject) object).getContainedObject();
            if (!targetDefinition.isResolved()) {
                targetDefinition.resolve(monitor);
            }
            TargetBundle[] targetBundles = targetDefinition.getAllBundles();
            if (targetBundles != null) {
                URI location;
                for (TargetBundle tb : targetBundles) {
                    if (monitor.isCanceled()) {
                        break;
                    }
                    location = tb.getBundleInfo().getLocation();
                    if (location != null) {
                        File file = new File(location);
                        if (file.getName().toLowerCase().endsWith(".jar")) {
                            try {
                                collector.add(new WorkbenchProxyObject(new ZipFile(file), null), monitor);
                            } catch (IOException e) {
                            }
                        }
                    }
                }
            }
            collector.done();
        }

        @Override
        public boolean isContainer() {
            return false;
        }

        @Override
        public ImageDescriptor getImageDescriptor(Object object) {
            ImageDescriptor imageDescriptor = null;
            Object containedObject = ((WorkbenchProxyObject) object).getContainedObject();
            String sharedImagesString = null;
            if (containedObject instanceof ITargetDefinition) {
                sharedImagesString = ISharedImages.IMG_OBJ_ELEMENT;
            } else if (containedObject instanceof ZipFile) {
                sharedImagesString = IDE.SharedImages.IMG_OBJ_PROJECT;
            } else if (containedObject instanceof ZipEntry) {
                String entryName = ((ZipEntry) containedObject).getName();
                entryName = entryName.substring(entryName.lastIndexOf(File.separator) + File.separator.length());
                imageDescriptor = PlatformUI.getWorkbench().getEditorRegistry().getImageDescriptor(entryName);
            }
            if (sharedImagesString != null) {
                imageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(sharedImagesString);
            }
            return imageDescriptor;
        }

        @Override
        public Object getParent(Object o) {
            return null;
        }

        @Override
        public ISchedulingRule getRule(Object object) {
            return null;
        }
    }

    /**
     * Instances of this class will be used to filter out of the navigator the
     * resources which file extension is not one of the expected.
     */
    private final class FileExtensionFilter extends ViewerFilter {
        @Override
        public boolean select(final Viewer theViewer, final Object parentElement, final Object element) {
            if (extensions == null || extensions.size() == 0 || extensions.contains("*")) {
                return true;
            }

            IResource resource = null;
            if (element instanceof IResource) {
                resource = (IResource) element;
            } else if (element instanceof IAdaptable) {
                resource = ((IAdaptable) element).getAdapter(IResource.class);
            }

            boolean isValid = true;

            if (resource != null && !resource.isDerived()) {
                isValid = validateResource(resource);
            } else if (element instanceof WorkbenchProxyObject) {
                WorkbenchProxyObject wpo = (WorkbenchProxyObject) element;
                if (wpo.isChecked()) {
                    isValid = wpo.isValid();
                } else {
                    isValid = validateWorkbenchProxyObject(wpo);
                }
            }
            return isValid;
        }

        private boolean validateResource(IResource resource) {
            boolean isValid = false;
            // In the case of a container, we'll probe its content to see if
            // it contains a valid file
            if (resource instanceof IContainer) {
                try {
                    final IResource[] members = ((IContainer) resource).members();
                    for (IResource member : members) {
                        isValid = validateResource(member);
                        if (isValid) {
                            // we found a valid resource in this folder, it
                            // is useless to probe any further
                            break;
                        }
                    }
                } catch (final CoreException e) {
                    // This shouldn't happen
                }
            }
            // In the case of a file, we'll check if its extension is one of
            // the expected
            else if (resource.getType() == IResource.FILE) {
                for (String ext : extensions) {
                    if (ext.equalsIgnoreCase(resource.getFileExtension())) {
                        isValid = true;
                        // found a valid file, no need to probe any
                        // further
                        break;
                    }
                }
            }
            return isValid;
        }

        private boolean validateWorkbenchProxyObject(WorkbenchProxyObject wpo) {
            boolean isValid = false;
            String fileExtension = wpo.getFileExtension();
            if (fileExtension != null) {
                for (String ext : extensions) {
                    if (fileExtension.equalsIgnoreCase(ext)) {
                        isValid = true;
                        break;
                    }
                }
            } else {
                for (Object obj : wpo.getChildren()) {
                    // this method is not called if wpo is the root, so all of
                    // its children are instances of WorkbenchProxyObject
                    isValid = validateWorkbenchProxyObject((WorkbenchProxyObject) obj);
                    if (isValid) {
                        break;
                    }
                }
            }
            wpo.setChecked(true);
            wpo.setValid(isValid);
            return isValid;
        }
    }
}
