/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.ui.properties.ext.widgets.reference.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eef.ide.ui.ext.widgets.reference.api.IEEFExtReferenceViewerFilterProvider;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;

/**
 * The viewer filter provider.
 * 
 * @author sbegaudeau
 */
public class EEFExtReferenceViewerFilterProvider implements IEEFExtReferenceViewerFilterProvider {

    @Override
    public List<ViewerFilter> getViewerFilters(ContextKind contextKind) {
        List<ViewerFilter> viewerFilters = new ArrayList<>();

        ViewerFilter viewFilter = new ViewerFilter() {
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                // Used to filter aird, odesign, etc
                if (element instanceof Resource) {
                    Session session = SessionManager.INSTANCE.getSession((Resource) element);
                    return session != null;
                }
                return true;
            }
        };

        viewerFilters.add(viewFilter);
        return viewerFilters;
    }

}
