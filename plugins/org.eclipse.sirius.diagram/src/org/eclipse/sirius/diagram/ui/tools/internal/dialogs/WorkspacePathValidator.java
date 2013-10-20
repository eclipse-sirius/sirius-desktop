/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.io.File;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.fieldassist.ControlDecoration;

import org.eclipse.sirius.common.tools.api.resource.FileProvider;

/**
 * A {@link IValidator} to validate the workspace path entered in the text
 * field.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class WorkspacePathValidator implements IValidator {

    private ControlDecoration controlDecoration;

    /**
     * Default constructor.
     * 
     * @param controlDecoration
     *            the {@link ControlDecoration}
     */
    public WorkspacePathValidator(ControlDecoration controlDecoration) {
        this.controlDecoration = controlDecoration;
    }

    /**
     * {@inheritDoc}
     */
    public IStatus validate(Object value) {
        IStatus status = Status.OK_STATUS;
        if (value instanceof String && controlDecoration.getControl().isEnabled()) {
            String text = (String) value;
            if (text.trim().length() > 0) {
                IPath path = new Path(text);
                File file = FileProvider.getDefault().getFile(path);
                if (file == null || !file.exists()) {
                    controlDecoration.show();
                    controlDecoration.setDescriptionText("The specified path does not correspond to an image in the workspace or in the runtime");
                    status = ValidationStatus.error("NonExistingImageResource");
                }
            }
        }
        if (status.isOK()) {
            controlDecoration.hide();
        }
        return status;
    }
}
