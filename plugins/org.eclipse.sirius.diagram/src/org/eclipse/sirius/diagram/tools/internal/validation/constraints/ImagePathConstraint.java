/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.internal.validation.constraints;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.business.api.image.ImageManager;
import org.eclipse.sirius.business.api.image.RichTextAttributeRegistry;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.tools.api.Messages;
import org.eclipse.sirius.diagram.tools.internal.validation.constraints.ImagePathWrappingStatus.ImagePathTarget;
import org.eclipse.sirius.ext.emf.edit.EditingDomainServices;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Constraint to validate the image path for.
 * <li>images associated to diagram nodes</li>
 * <li>images displayed in rich text editor</li> <br/>
 * An image path is considered as wrong if
 * <li>the image has an absolute path format</li>
 * <li>the image can not be found</li>
 * 
 * @author lfasani
 */
public class ImagePathConstraint extends AbstractConstraint {
    private static final String HTML_IMAGE_ABSOLUTE_PATH_PATTERN = "<img.*?src=\"(file:/.*?)\".*?/>"; //$NON-NLS-1$

    @Override
    public IStatus validate(IValidationContext ctx) {
        List<IStatus> failureStatuses = new ArrayList<>();
        if (ctx.getEventType() == EMFEventType.NULL) {
            EObject eObj = ctx.getTarget();

            if (Arrays.asList(ViewpointPackage.eINSTANCE, DiagramPackage.eINSTANCE).contains(eObj.eClass().getEPackage())) {
                if (eObj instanceof DRepresentation) {
                    DRepresentationDescriptor representationDescriptor = new DRepresentationQuery((DRepresentation) eObj).getRepresentationDescriptor();
                    if (representationDescriptor != null) {
                        failureStatuses.addAll(validateImagePathInRichText(representationDescriptor, ctx, ImagePathWrappingStatus.ImagePathTarget.DREPRESENTATION_DESCRIPTOR));
                    }
                } else if (eObj instanceof WorkspaceImage) {
                    validateWorkspaceImagePath((WorkspaceImage) eObj, ctx, failureStatuses);
                }
            } else {
                // case of the semantic objects
                failureStatuses.addAll(validateImagePathInRichText(eObj, ctx, ImagePathWrappingStatus.ImagePathTarget.SEMANTIC_TARGET));
            }
        }
        if (failureStatuses.isEmpty()) {
            return ctx.createSuccessStatus();
        } else {
            return ConstraintStatus.createMultiStatus(ctx, failureStatuses);
        }
    }

    private void validateWorkspaceImagePath(WorkspaceImage workspaceImage, IValidationContext ctx, List<IStatus> statuses) {
        String workspacePath = workspaceImage.getWorkspacePath();
        boolean exists = FileProvider.getDefault().exists(new Path(workspacePath));
        if (!exists) {
            EObject eContainer = workspaceImage.eContainer();
            String repDescName = ""; //$NON-NLS-1$
            if (eContainer instanceof DDiagramElement) {
                DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(new EObjectQuery(eContainer).getRepresentation().get()).getRepresentationDescriptor();
                repDescName = representationDescriptor.getName();
            }
            String message = MessageFormat.format(Messages.ImagePathConstraint_workspaceImagePathError, workspacePath, new EditingDomainServices().getLabelProviderText(workspaceImage.eContainer()),
                    repDescName);
            // The constraint needs to be associated to the diagram element
            ConstraintStatus failureStatus = new ImagePathWrappingStatus(ConstraintStatus.createStatus(ctx, workspaceImage.eContainer(), null, "{0}", message), //$NON-NLS-1$
                    ImagePathWrappingStatus.ImagePathTarget.WORKSPACE_IMAGE, workspacePath);
            statuses.add(failureStatus);
        }
    }

    private List<IStatus> validateImagePathInRichText(EObject eObject, IValidationContext ctx, ImagePathTarget imagePathTarget) {
        List<IStatus> statuses = new ArrayList<>();
        EList<EAttribute> attrs = eObject.eClass().getEAllAttributes();
        Set<EAttribute> richTextAttributes = RichTextAttributeRegistry.INSTANCE.getEAttributes();
        for (EAttribute attr : attrs) {
            if (richTextAttributes.contains(attr)) {
                Object stringObj = eObject.eGet(attr);
                if (stringObj instanceof String) {
                    String htmlText = (String) stringObj;

                    statuses.addAll(checkAbsolutePathInString(ctx, eObject, htmlText, attr, imagePathTarget));
                    statuses.addAll(checkRelativePathInString(ctx, eObject, htmlText, attr, imagePathTarget));
                }
            }
        }
        return statuses;
    }

    private Collection<IStatus> checkRelativePathInString(IValidationContext ctx, EObject eObject, String strValue, EAttribute attr, ImagePathTarget imagePathTarget) {
        Collection<IStatus> statuses = new ArrayList<>();
        Pattern pattern = Pattern.compile(ImageManager.HTML_IMAGE_PATH_PATTERN);
        Matcher matcher = pattern.matcher(strValue);

        List<String> alreadyCheckedPath = new ArrayList<>();
        while (matcher.find()) {
            if (matcher.groupCount() == 1) {
                String path = matcher.group(1);
                boolean exists = FileProvider.getDefault().exists(new Path(path));
                if (!exists && !alreadyCheckedPath.contains(path)) {
                    alreadyCheckedPath.add(path);
                    String message = MessageFormat.format(Messages.ImagePathConstraint_relativePathError, path, new EditingDomainServices().getLabelProviderText(eObject));
                    ImagePathWrappingStatus imagePathWrappingStatus = new ImagePathWrappingStatus((ConstraintStatus) ctx.createFailureStatus(new Object[] { message }), imagePathTarget, path);
                    imagePathWrappingStatus.setEAttribute(attr);
                    statuses.add(imagePathWrappingStatus);
                }
            }
        }
        return statuses;
    }

    private Collection<IStatus> checkAbsolutePathInString(IValidationContext ctx, EObject eObject, String strValue, EAttribute attr, ImagePathTarget imagePathTarget) {
        Collection<IStatus> statuses = new ArrayList<>();
        Pattern pattern = Pattern.compile(HTML_IMAGE_ABSOLUTE_PATH_PATTERN);
        Matcher matcher = pattern.matcher(strValue);

        while (matcher.find()) {
            if (matcher.groupCount() == 1) {
                String message = MessageFormat.format(Messages.ImagePathConstraint_absolutePathError, matcher.group(1), new EditingDomainServices().getLabelProviderText(eObject));
                ImagePathWrappingStatus imagePathWrappingStatus = new ImagePathWrappingStatus((ConstraintStatus) ctx.createFailureStatus(new Object[] { message }), imagePathTarget, matcher.group(1));
                imagePathWrappingStatus.setEAttribute(attr);
                statuses.add(imagePathWrappingStatus);
            }
        }
        return statuses;
    }
}
