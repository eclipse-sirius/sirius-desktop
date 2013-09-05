/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration.resource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class encapsulating the logic of textual transformation for the Color
 * definitions migration.
 * 
 * @author cbrun
 * 
 */
public class ColorsTextMigration {

    private String content;

    /**
     * Create a new migration.
     * 
     * @param content
     *            the content of the file to migrate.
     */
    public ColorsTextMigration(final String content) {
        this.content = content;
    }

    /**
     * return the file content after refactoring it.
     * 
     * @return the file content after refactoring it.
     */
    public String getOdesignRefactoredContent() {
        renameNamedColorsHavingValue();
        renameNamedColorsHavingDefaultValue();
        // removeColorNameAttribute();
        // refactorAttributeValuestoReferenceToSTDEnvironemnt();
        // changeEPackageDeclarationForRGBValuesAndScaleValues();
        return content;
    }

    /**
     * return the file content after refactoring it.
     * 
     * @return the file content after refactoring it.
     */
    public String getAirdRefactoredContent() {
        renamedNamedColorToRGBColor();
        return content;
    }

    private void renamedNamedColorToRGBColor() {
        final Pattern pattern = Pattern.compile("=\"viewpoint:(NamedColor|ScaleValue|RGBColor)\"");
        final Matcher matcher = pattern.matcher(this.content);
        final StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "=\"viewpoint:" + "RGBValues" + "\"");
        }
        matcher.appendTail(sb);
        this.content = sb.toString();
    }

    private void renameNamedColorsHavingValue() {
        final Pattern pattern = Pattern.compile("xsi:type=\"viewpoint:NamedColor\" colorName=\"([a-zA-Z0-9_]+)\"[^>]*?/>");
        final Matcher matcher = pattern.matcher(this.content);
        final StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "xsi:type=\"description:SystemColor\" href=\"platform:/plugin/org.eclipse.sirius/model/Environment.xmi#//@systemColors/@entries[name='" + matcher.group(1)
                    + "']\" />");
        }
        matcher.appendTail(sb);
        this.content = sb.toString();
    }

    private void renameNamedColorsHavingDefaultValue() {
        final Pattern pattern = Pattern.compile("xsi:type=\"viewpoint:NamedColor\"[^>]*?/>");
        final Matcher matcher = pattern.matcher(this.content);
        final StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "xsi:type=\"description:SystemColor\" href=\"platform:/plugin/org.eclipse.sirius/model/Environment.xmi#//@systemColors/@entries[name='" + "black"
                    + "']\" />");
        }
        matcher.appendTail(sb);
        this.content = sb.toString();
    }

}
