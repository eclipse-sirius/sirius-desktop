/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.editor.tabbar.ITabbarContributor;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * This implementation of {@link ITabbarContributorProvider} looks for extension in the extension point.
 * 
 * @author Florian Barbin
 *
 */
public class ExtensionPointTabbarContributorProvider implements ITabbarContributorProvider {

    /**
     * The tabbar extension point ID.
     */
    public static final String EXTENSION_ID = "org.eclipse.sirius.diagram.ui.tabbarContributor"; //$NON-NLS-1$

    private List<ITabbarContributor> loadedExtensions;

    @Override
    public boolean hasContributor() {
        if (loadedExtensions == null) {
            loadExtensions();
        }
        return !loadedExtensions.isEmpty();
    }

    @Override
    public ITabbarContributor getContributor() {
        return getContributor(null);
    }

    @Override
    public ITabbarContributor getContributor(ISelection selection) {
        if (loadedExtensions == null) {
            loadExtensions();
        }
        for (ITabbarContributor extension : loadedExtensions) {
            if (extension.accept(selection)) {
                return extension;
            }
        }
        return null;
    }

    private void loadExtensions() {
        this.loadedExtensions = new ArrayList<ITabbarContributor>();

        IConfigurationElement[] configurationElements = EclipseUtil.getConfigurationElementsFor(EXTENSION_ID);
        for (IConfigurationElement configurationElement : configurationElements) {
            try {
                Object contribution = configurationElement.createExecutableExtension("class"); //$NON-NLS-1$
                if (contribution instanceof ITabbarContributor) {
                    loadedExtensions.add((ITabbarContributor) contribution);
                }
            } catch (CoreException e) {
                String extensionId = null;
                Object parent = configurationElement.getParent();
                if (parent instanceof IExtension) {
                    extensionId = ((IExtension) parent).getUniqueIdentifier();
                }
                String message = StringUtil.EMPTY_STRING;
                if (extensionId != null) {
                    message = MessageFormat.format(Messages.DefaultTabbarContributorProvider_contributionError_withId, extensionId);
                } else {
                    message = Messages.DefaultTabbarContributorProvider_contributionError;
                }
                SiriusPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, DiagramUIPlugin.ID, message, e));
            }
        }
    }
}
