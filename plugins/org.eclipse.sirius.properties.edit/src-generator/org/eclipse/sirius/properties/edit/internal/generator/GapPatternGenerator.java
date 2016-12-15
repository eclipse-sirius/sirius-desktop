/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.edit.internal.generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

/**
 * Utility class used to generate the Java classes used for the generation gap
 * pattern of the EMF Edit part.
 * 
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle:multiplestringliterals" })
public final class GapPatternGenerator {
    /**
     * The constructor.
     */
    private GapPatternGenerator() {
        // do nothing
    }

    /**
     * The entry point of our generator.
     * 
     * @param args
     *            The arguments (unused)
     */
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir"); //$NON-NLS-1$
        Path siriusPropertiesEditProjectPath = Paths.get(userDir);
        Path siriusPropertiesProjectPath = siriusPropertiesEditProjectPath.getParent().resolve("org.eclipse.sirius.properties"); //$NON-NLS-1$
        Path ecoreModel = siriusPropertiesProjectPath.resolve("model/properties.ecore"); //$NON-NLS-1$

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl()); //$NON-NLS-1$
        URI uri = URI.createFileURI(ecoreModel.toAbsolutePath().toString());

        Path outputFolderPath = siriusPropertiesEditProjectPath.resolve("src-spec/org/eclipse/sirius/properties/provider"); //$NON-NLS-1$

        Resource resource = resourceSet.getResource(uri, true);
        if (resource != null && resource.getContents().size() == 1 && resource.getContents().get(0) instanceof EPackage) {
            EPackage ePackage = (EPackage) resource.getContents().get(0);

            GapPatternGenerator.generateAdapterFactory(outputFolderPath, ePackage);
            GapPatternGenerator.generateDescriptionChildCreationExtender(outputFolderPath, ePackage);
            GapPatternGenerator.generateValidationChildCreationExtender(outputFolderPath, ePackage);

            List<EClassifier> eClassifiers = ePackage.getEClassifiers();
            for (EClassifier eClassifier : eClassifiers) {
                if (eClassifier instanceof EClass) {
                    GapPatternGenerator.generateItemProvider(outputFolderPath, (EClass) eClassifier);
                }
            }
        }
    }

    /**
     * Appends the copyrights to the given string builder and returns it.
     * 
     * @param builder
     *            The string builder
     * @return The given string builder
     */
    private static StringBuilder appendCopyright(StringBuilder builder) {
        builder.append("/**").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * Copyright (c) 2017 Obeo.").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * All rights reserved. This program and the accompanying materials").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * are made available under the terms of the Eclipse Public License v1.0").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * which accompanies this distribution, and is available at").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * http://www.eclipse.org/legal/epl-v10.html").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" *").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * Contributors:").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" *    Obeo - initial API and implementation").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" *").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" */").append(System.lineSeparator()); //$NON-NLS-1$
        return builder;
    }

    /**
     * Returns the given word with the first character in upper case.
     * 
     * @param word
     *            The word
     * @return The given word with the first character in upper case
     */
    private static String toUpperFirst(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    /**
     * Returns the given word with the first character in lower case.
     * 
     * @param word
     *            The word
     * @return The given word with the first character in lower case
     */
    private static String toLowerFirst(String word) {
        return word.substring(0, 1).toLowerCase() + word.substring(1);
    }

    /**
     * Writes the file at the given location with the given content.
     * 
     * @param outputFilePath
     *            The file location
     * @param builder
     *            The content
     * @param force
     *            <code>true</code> to indicate that we should overwrite the
     *            existing file, if any, <code>false</code> otherwise
     */
    private static void writeSourceFile(Path outputFilePath, StringBuilder builder, boolean force) {
        File parentFolder = outputFilePath.toFile().getParentFile();
        if (!parentFolder.exists()) {
            parentFolder.mkdirs();
        }
        if (!outputFilePath.toFile().exists() || force) {
            try {
                Files.write(outputFilePath, builder.toString().getBytes());
            } catch (IOException e) {
                // CHECKSTYLE:OFF
                e.printStackTrace();
                // CHECKSTYLE:ON
            }
        }
    }

    /**
     * Generates the adapter factory for the given EPackage in the given output
     * folder.
     * 
     * @param outputFolderPath
     *            The path of the output folder
     * @param ePackage
     *            The EPackage
     */
    private static void generateAdapterFactory(Path outputFolderPath, EPackage ePackage) {
        StringBuilder builder = GapPatternGenerator.appendCopyright(new StringBuilder());

        builder.append("package org.eclipse.sirius.properties.provider;").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(System.lineSeparator());
        builder.append("import org.eclipse.emf.common.notify.Adapter;").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(System.lineSeparator());
        builder.append("/**").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * Subclass used to not have to modify the generated code.").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" *").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * @author ").append(System.getProperty("user.name")).append(System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
        builder.append(" */").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append("public class PropertiesItemProviderAdapterFactorySpec extends PropertiesItemProviderAdapterFactory {").append(System.lineSeparator()); //$NON-NLS-1$

        for (EClassifier eClassifier : ePackage.getEClassifiers()) {
            if (eClassifier instanceof EClass && !((EClass) eClassifier).isAbstract() && !((EClass) eClassifier).isInterface()) {
                builder.append(System.lineSeparator());
                builder.append("    @Override").append(System.lineSeparator()); //$NON-NLS-1$
                builder.append("    public Adapter create" + GapPatternGenerator.toUpperFirst(eClassifier.getName()) + "Adapter() {").append(System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
                builder.append("        if (" + GapPatternGenerator.toLowerFirst(eClassifier.getName()) + "ItemProvider == null) {").append(System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
                builder.append("            " + GapPatternGenerator.toLowerFirst(eClassifier.getName()) + "ItemProvider = new " + GapPatternGenerator.toUpperFirst(eClassifier.getName()) //$NON-NLS-1$ //$NON-NLS-2$
                        + "ItemProviderSpec(this);") //$NON-NLS-1$
                        .append(System.lineSeparator());
                builder.append("        }").append(System.lineSeparator()); //$NON-NLS-1$
                builder.append(System.lineSeparator());
                builder.append("        return " + GapPatternGenerator.toLowerFirst(eClassifier.getName()) + "ItemProvider;").append(System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
                builder.append("    }").append(System.lineSeparator()); //$NON-NLS-1$
            }
        }

        builder.append("}").append(System.lineSeparator()); //$NON-NLS-1$

        String className = GapPatternGenerator.toUpperFirst(ePackage.getName()) + "ItemProviderAdapterFactorySpec.java"; //$NON-NLS-1$
        Path outputFilePath = outputFolderPath.resolve(className);

        GapPatternGenerator.writeSourceFile(outputFilePath, builder, true);
    }

    /**
     * Generates the description child creation extender for the given EPackage
     * in the given output folder.
     * 
     * @param outputFolderPath
     *            The path of the output folder
     * @param ePackage
     *            The EPackage
     */
    private static void generateDescriptionChildCreationExtender(Path outputFolderPath, EPackage ePackage) {
        StringBuilder builder = GapPatternGenerator.appendCopyright(new StringBuilder());
        builder.append("package org.eclipse.sirius.properties.provider;").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(System.lineSeparator());
        builder.append("import org.eclipse.sirius.properties.provider.PropertiesItemProviderAdapterFactory.DescriptionChildCreationExtender;").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(System.lineSeparator());
        builder.append("/**").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * Subclass used to not have to modify the generated code.").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" *").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * @author ").append(System.getProperty("user.name")).append(System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
        builder.append(" */").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append("public class DescriptionChildCreationExtenderSpec extends DescriptionChildCreationExtender {") //$NON-NLS-1$
                .append(System.lineSeparator());
        builder.append("}").append(System.lineSeparator()); //$NON-NLS-1$

        String className = "DescriptionChildCreationExtenderSpec.java"; //$NON-NLS-1$
        Path outputFilePath = outputFolderPath.resolve(className);

        GapPatternGenerator.writeSourceFile(outputFilePath, builder, false);
    }

    /**
     * Generates the validation child creation extender for the given EPackage
     * in the given output folder.
     * 
     * @param outputFolderPath
     *            The path of the output folder
     * @param ePackage
     *            The EPackage
     */
    private static void generateValidationChildCreationExtender(Path outputFolderPath, EPackage ePackage) {
        StringBuilder builder = GapPatternGenerator.appendCopyright(new StringBuilder());
        builder.append("package org.eclipse.sirius.properties.provider;").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(System.lineSeparator());
        builder.append("import org.eclipse.sirius.properties.provider.PropertiesItemProviderAdapterFactory.ValidationChildCreationExtender;").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(System.lineSeparator());
        builder.append("/**").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * Subclass used to not have to modify the generated code.").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" *").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * @author ").append(System.getProperty("user.name")).append(System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
        builder.append(" */").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append("public class ValidationChildCreationExtenderSpec extends ValidationChildCreationExtender {") //$NON-NLS-1$
                .append(System.lineSeparator());
        builder.append("}").append(System.lineSeparator()); //$NON-NLS-1$

        String className = "ValidationChildCreationExtenderSpec.java"; //$NON-NLS-1$
        Path outputFilePath = outputFolderPath.resolve(className);

        GapPatternGenerator.writeSourceFile(outputFilePath, builder, false);
    }

    /**
     * Generates the item provider for the given EClass in the given output
     * folder.
     * 
     * @param outputFolderPath
     *            The path of the output folder
     * @param eClass
     *            The EClass
     */
    private static void generateItemProvider(Path outputFolderPath, EClass eClass) {
        StringBuilder builder = GapPatternGenerator.appendCopyright(new StringBuilder());
        builder.append("package org.eclipse.sirius.properties.provider;").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(System.lineSeparator());
        builder.append("import org.eclipse.emf.common.notify.AdapterFactory;").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(System.lineSeparator());
        builder.append("/**").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * Subclass used to not have to modify the generated code.").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" *").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" * @author sbegaudeau").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(" */").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append("public class " + GapPatternGenerator.toUpperFirst(eClass.getName()) + "ItemProviderSpec extends " + GapPatternGenerator.toUpperFirst(eClass.getName()) + "ItemProvider {") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                .append(System.lineSeparator());
        builder.append(System.lineSeparator());
        builder.append("    /**").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append("     * The constructor.").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append("     *").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append("     * @param adapterFactory").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append("     *            The adapter factory").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append("     */").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append("    public " + GapPatternGenerator.toUpperFirst(eClass.getName()) + "ItemProviderSpec(AdapterFactory adapterFactory) {").append(System.lineSeparator()); //$NON-NLS-1$ //$NON-NLS-2$
        builder.append("        super(adapterFactory);").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append("    }").append(System.lineSeparator()); //$NON-NLS-1$
        builder.append(System.lineSeparator());
        builder.append("}").append(System.lineSeparator()); //$NON-NLS-1$

        String className = GapPatternGenerator.toUpperFirst(eClass.getName()) + "ItemProviderSpec.java"; //$NON-NLS-1$
        Path outputFilePath = outputFolderPath.resolve(className);

        GapPatternGenerator.writeSourceFile(outputFilePath, builder, false);
    }
}
