/*******************************************************************************
 * Copyright (c) 2021, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.sirius.business.api.image.Base64ImageHelper;
import org.eclipse.sirius.business.api.image.ImageManager;
import org.eclipse.sirius.business.api.image.ImageManagerProvider;
import org.eclipse.sirius.business.api.image.RichTextAttributeRegistry;

/**
 * A precommit listener that will change the image Base64 encoding to a path to an image that will be created in the
 * "images" folder of the project containing the resource of the notifier.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public class UpdateBase64ImageEncodingPreCommitListener extends ResourceSetListenerImpl {

    private final DAnalysisSessionImpl dAnalysisSessionImpl;

    /**
     * @param dAnalysisSessionImpl
     */
    UpdateBase64ImageEncodingPreCommitListener(DAnalysisSessionImpl dAnalysisSessionImpl) {
        this.dAnalysisSessionImpl = dAnalysisSessionImpl;
    }

    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        List<Notification> notifications = event.getNotifications();
        List<Notification> notificationToCreatedFiles = getNotificationToCreatedFiles(notifications);

        if (notificationToCreatedFiles.size() > 0) {
            RecordingCommandWithCustomUndo changeIdRecordingCommand = new RecordingCommandWithCustomUndo(this.dAnalysisSessionImpl.getTransactionalEditingDomain()) {

                /**
                 * Keep the list of created files in case of undo.
                 */
                private Map<String, String> allCreatedFiles = new LinkedHashMap<>();

                @Override
                protected void doExecute() {

                    for (Notification notification : notificationToCreatedFiles) {
                        Map<String, String> createdFileAndUpdatedAttribute = new Base64ImageHelper().createFileAndUpdateAttribute((EObject) notification.getNotifier(),
                                (EAttribute) notification.getFeature());
                        allCreatedFiles.putAll(createdFileAndUpdatedAttribute);
                    }
                }

                @Override
                public void undo() {

                    ImageManager imageManager = ImageManagerProvider.getImageManager();
                    imageManager.undoCreatedFiles(dAnalysisSessionImpl, allCreatedFiles);

                    super.undo();
                }

                @Override
                public void redo() {
                    ImageManager imageManager = ImageManagerProvider.getImageManager();
                    imageManager.redoCreateFiles(dAnalysisSessionImpl, allCreatedFiles);
                    super.redo();
                }
            };
            return changeIdRecordingCommand;
        }
        return null;
    }

    /**
     * Return the notifier that must be processed to convert a Base64 string to a file.
     */
    private List<Notification> getNotificationToCreatedFiles(List<Notification> notifications) {
        List<Notification> notificationToKeep = new ArrayList<>();

        for (Notification notification : notifications) {
            Object featureObj = notification.getFeature();
            Object newValue = notification.getNewValue();
            Object notifierObj = notification.getNotifier();
            Set<EAttribute> eAttributes = RichTextAttributeRegistry.INSTANCE.getEAttributes();
            if (eAttributes.contains(featureObj) && newValue instanceof String && notifierObj instanceof EObject) {
                String stringValue = (String) newValue;

                Pattern pattern = Pattern.compile(Base64ImageHelper.BASE64_IMAGE_PATTERN_WITH_SUBSTRINGS);
                Matcher matcher = pattern.matcher(stringValue);
                if (matcher.find()) {
                    notificationToKeep.add(notification);
                }
            }
        }
        return notificationToKeep;
    }
}
