/*******************************************************************************
 * Copyright (c) 2021, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.image;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.image.ImageManager.CreateImageFileProvider;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * This helper provides utility methods to convert base64 string to an image.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public class Base64ImageHelper {
    /**
     * Pattern to find the base64 string.
     */
    public static final String BASE64_IMAGE_PATTERN_WITH_SUBSTRINGS = "\"data:image/([a-zA-Z]{3,4});base64,(.*?)\""; //$NON-NLS-1$

    private static final String BASE64_IMAGE_PATTERN = "data:image/[a-zA-Z]{3,4};base64,"; //$NON-NLS-1$

    /**
     * This method converts the base64 string from the String attribute of the given eObject<br/>
     * It creates a file, into the images folder of the project containing the eObject resource, and replaces the base64
     * string by a workspace relative path to the created file.
     * 
     * @param eObject
     *            the eObject to change
     * @param attr
     *            the attribute that must be of type String
     * @return A map with base64 strings associated to the path to the created file.
     */
    public Map<String, String> createFileAndUpdateAttribute(EObject eObject, EAttribute attr) {
        // Matcher matcher = pattern.matcher(notification.getNewStringValue());
        String strValue = (String) eObject.eGet(attr);
        if (strValue != null) {
            Map<String, String> pathToImages = createFiles(eObject, strValue);

            updateField(eObject, attr, strValue, pathToImages);
            return pathToImages;
        }

        return new HashMap<String, String>();
    }

    private void updateField(EObject eObject, EAttribute attr, String strValue, Map<String, String> pathToImages) {
        String newStringValue = strValue;
        // change the attribute value to replace with a path to the created file.
        for (Entry<String, String> entry : pathToImages.entrySet()) {
            newStringValue = newStringValue.replaceAll(BASE64_IMAGE_PATTERN + Pattern.quote(entry.getKey()), entry.getValue());
        }
        if (!Objects.equals(newStringValue, strValue)) {
            eObject.eSet(attr, newStringValue);
        }
    }

    private Map<String, String> createFiles(EObject notifier, String strValue) {
        Pattern pattern = Pattern.compile(BASE64_IMAGE_PATTERN_WITH_SUBSTRINGS);
        Matcher matcher = pattern.matcher(strValue);
        Map<String, String> pathToImages = new LinkedHashMap<>();

        while (matcher.find()) {
            if (matcher.groupCount() == 2) {
                ImageManager imageManager = ImageManagerProvider.getImageManager();

                String simpleImageName = ImageManager.generateName(matcher.group(1));
                try {
                    CreateImageFileProvider createFileFunc = imageManager.getCreateFileFunc(notifier, simpleImageName, matcher.group(2));
                    createFileFunc.exec();
                    pathToImages.put(matcher.group(2), createFileFunc.getFileName());
                } catch (IOException | CoreException e) {
                    SiriusPlugin.getDefault().error(MessageFormat.format(Messages.ImageManager_imageCreationFailure, simpleImageName), e);
                }
            }
        }
        return pathToImages;
    }
}
