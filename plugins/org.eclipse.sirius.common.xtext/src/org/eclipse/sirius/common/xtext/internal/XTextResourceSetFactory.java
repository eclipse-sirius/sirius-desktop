/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.xtext.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;

/**
 * Class overriding the default {@link ResourceSet} factory to correctly setup
 * the resourceSet when XText is used.
 * 
 * @author cedric
 * 
 */
public class XTextResourceSetFactory extends org.eclipse.sirius.common.tools.api.resource.ResourceSetFactory {

    @Override
    public ResourceSet createResourceSet(URI resourceURI) {
        XtextResourceSet set = new XtextResourceSet();
        IProject prj = findProjectFromURI(resourceURI);
        if (prj != null) {
            try {
                new ResourceSetClasspathConfigurator().configure(set, prj);
            } catch (NoClassDefFoundError ncdfe) {
                // The JDT is not present, but it's OK.
            }
        }

        /*
         * We enable the "LIVE_SCOPE" in Xtext so that the scoping will look for
         * a IResourceDescription first in the ResourecSet, then in dirty
         * editors and then in the index. Without this cross-references might
         * get broken when saving, see Bug 448304.
         */
        set.getLoadOptions().put(org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider.LIVE_SCOPE, Boolean.TRUE);
        return set;
    }

    private IProject findProjectFromURI(URI resourceURI) {
        if (resourceURI.isPlatformResource() && resourceURI.segmentCount() > 0) {
            final String projectName = resourceURI.segment(1);
            return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        }
        return null;
    }
}
