/*******************************************************************************
 * Copyright (c) 2021, 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.io.File;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.sirius.diagram.ui.business.api.image.ITreeImagesContentProvider;
import org.eclipse.sirius.diagram.ui.business.api.image.ImageFiltersUtils;
import org.eclipse.ui.model.WorkbenchContentProvider;

/**
 * A tree content provider that manages a {@link java.io.File} structure for images in workspace.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class ImageWorkspaceContentProvider extends WorkbenchContentProvider implements ITreeImagesContentProvider {

    @Override
    public Object[] getChildren(Object element) {
        Object[] superResult = super.getChildren(element);
        Comparator<Object> comparator = new Comparator<Object>() {
            @SuppressWarnings("unchecked")
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Comparable && o2 instanceof Comparable) {
                    return ((Comparable<Object>) o1).compareTo(o2);
                }
                return String.CASE_INSENSITIVE_ORDER.compare(o1.toString(), o2.toString());
            }
        };
        Set<Object> result = new TreeSet<>(comparator);
        for (Object o : superResult) {
            result.add(o);
        }
        return result.toArray();
    }

    @Override
    public Optional<File> getImageFile(Object element) {
        Optional<File> result = Optional.empty();
        if (element instanceof IFile) {
            IFile file = (IFile) element;
            if (ImageFiltersUtils.isSupportedImageFile(file.getName())) {
                result = Optional.of(file.getLocation().toFile());
            }
        }
        return result;
    }

    @Override
    public Optional<String> getPath(Object element) {
        Optional<String> result = Optional.empty();
        if (element instanceof IResource) {
            IResource resource = (IResource) element;
            result = Optional.of(resource.getFullPath().toString().replaceFirst("/", "")); //$NON-NLS-1$//$NON-NLS-2$
        }
        return result;
    }

    @Override
    public boolean isImage(Object element) {
        return element instanceof IFile;
    }
}
