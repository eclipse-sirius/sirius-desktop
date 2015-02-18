/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.validation.description.constraints;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;

/**
 * Abstract Constraint to validate image references.
 * 
 * @author bgrouhan
 */
public abstract class AbstractValidImageConstraint extends AbstractConstraint {

    @Override
    public IStatus validate(IValidationContext ctx) {
        IStatus status = ctx.createSuccessStatus();
        // In the case of batch mode.
        if (ctx.getEventType() == EMFEventType.NULL) {
            EObject eObj = ctx.getTarget();
            String path = "";
            EAttribute[] attrs = getImagePathAttributes();
            ArrayList<IStatus> statuses = new ArrayList<IStatus>();
            ResourceSet rs = eObj.eResource().getResourceSet();
            for (EAttribute attr : attrs) {
                if (attr.getEContainingClass().isInstance(eObj)) {
                    path = (String) eObj.eGet(attr);
                    statuses.add(validateImagePath(ctx, rs, path));
                }
            }
            if (statuses.size() > 0) {
                status = ConstraintStatus.createMultiStatus(ctx, statuses);
            }
        }
        return status;
    }

    /**
     * Method to get all the EAttributes corresponding to image paths in the
     * package.
     * 
     * @return An array of the EAttributes.
     */
    public abstract EAttribute[] getImagePathAttributes();

    private IStatus validateImagePath(IValidationContext ctx, ResourceSet rs, String path) {
        IStatus result = ctx.createSuccessStatus();
        // when path is empty, success (even when a path is needed, because
        // there is another validation rule for that)
        if (!StringUtil.isEmpty(path)) {
            if (!validateExtension(path)) {
                result = ctx.createFailureStatus(new Object[] { "The path '" + path + "' does not correspond to an image." });
            }
            if (!validateExistence(path, rs)) {
                result = ctx.createFailureStatus(new Object[] { "The image '" + path + "' does not exist." });
            }
        }
        return result;
    }

    private boolean validateExistence(String path, ResourceSet rs) {
        URIConverter uriConverter = rs.getURIConverter();
        URI workspaceURI = URI.createPlatformResourceURI(path, true);
        URI pluginsURI = URI.createPlatformPluginURI(path, true);
        return uriConverter.exists(workspaceURI, null) || uriConverter.exists(pluginsURI, null);
    }

    private boolean validateExtension(String path) {
        boolean isValid = false;
        String extension = new Path(path).getFileExtension();
        for (ImageFileFormat element : ImageFileFormat.VALUES) {
            if (element.getName().equalsIgnoreCase(extension)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
